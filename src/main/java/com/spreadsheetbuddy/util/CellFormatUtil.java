package com.spreadsheetbuddy.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Cell formatting utility class.
 */
public class CellFormatUtil {
    private static Logger logger = LoggerFactory.getLogger(CellFormatUtil.class);

    /**
     * Gets supported cell format types for populating the menu's spinner.
     *
     * @return the cell format types
     */
    public static ObservableList<String> getSupportedCellFormats() {
        List<String> list = Arrays.asList("General", "Number", "Formula", "Date", "List", "Image", "Dollar", "Image");
        return FXCollections.observableArrayList(list);

    }

    private static Object getCellValue(final Workbook workbook, final Cell cell) {
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

        if (cell == null) {
            return null;
        }

        final Object result;

        switch (cell.getCellType()) {
            case BLANK:
            case _NONE:
                result = null;
                break;
            case BOOLEAN:
                result = cell.getBooleanCellValue();
                break;
            case ERROR:
                result = getErrorResult(cell);
                break;
            case FORMULA:
                result = evaluator.evaluateFormulaCell(cell); // TODO: Expand support for FormulaCells
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    result = cell.getDateCellValue();
                } else {
                    result = getDoubleAsNumber(cell.getNumericCellValue());
                }
                break;
            case STRING:
                result = cell.getRichStringCellValue().getString();
                break;
            default:
                throw new IllegalStateException("Unknown cell type: " + cell.getCellType());
        }

        logger.debug("cell ({},{}) resolved to value: {}", cell.getRowIndex(), cell.getColumnIndex(), result);

        return result;
    }

    private static String getErrorResult(final Cell cell) {
        try {
            return FormulaError.forInt(cell.getErrorCellValue()).getString();
        } catch (final RuntimeException e) {
            logger.debug("Getting error code for ({},{}) failed!: {}", cell.getRowIndex(), cell.getColumnIndex(), e
                    .getMessage());
            if (cell instanceof XSSFCell) {
                // hack to get error string, which is available
                return ((XSSFCell) cell).getErrorCellString();
            } else {
                logger
                        .error("Couldn't handle unexpected error scenario in cell: ({},{})", cell.getRowIndex(), cell
                                .getColumnIndex());
                throw e;
            }
        }
    }


//    private static String getFormulaCellValue(Workbook workbook, Cell cell) {
//        // first try with a cached/precalculated value
//        try {
//            double numericCellValue = cell.getNumericCellValue();
//            return getNumericCellValueAsString(cell.getCellStyle(), numericCellValue);
//        } catch (Exception e) {
//            if (logger.isInfoEnabled()) {
//                logger.info("Failed to fetch cached/precalculated formula value of cell: " + cell, e);
//            }
//        }
//
//        // evaluate cell first, if possible
//        final Cell evaluatedCell = getEvaluatedCell(workbook, cell);
//        if (evaluatedCell != null) {
//            return getCellValue(workbook, evaluatedCell);
//        } else {
//            // last resort: return the string formula
//            return cell.getCellFormula();
//        }
//    }

    private static Number getDoubleAsNumber(final double value) {
        if (value % 1 == 0 && value <= Integer.MAX_VALUE) {
            return (int) value;
        } else {
            return value;
        }
    }

    private static String getNumericCellValueAsString(final CellStyle cellStyle, final double cellValue) {
        final int formatIndex = cellStyle.getDataFormat();
        String formatString = cellStyle.getDataFormatString();
        if (formatString == null) {
            formatString = BuiltinFormats.getBuiltinFormat(formatIndex);
        }
        final DataFormatter formatter = new DataFormatter();
        return formatter.formatRawCellContents(cellValue, formatIndex, formatString);
    }

//    public static Style getCellStyle(Workbook workbook, Cell cell) {
//        if (cell == null) {
//            return Style.NO_STYLE;
//        }
//        final CellStyle cellStyle = cell.getCellStyle();
//
//        final int fontIndex = cellStyle.getFontIndexAsInt();
//        final Font font = workbook.getFontAt(fontIndex);
//        final StyleBuilder styleBuilder = new StyleBuilder();
//
//        // Font bold, italic, underline
//        if (font.getBold()) {
//            styleBuilder.bold();
//        }
//        if (font.getItalic()) {
//            styleBuilder.italic();
//        }
//        if (font.getUnderline() != FontUnderline.NONE.getByteValue()) {
//            styleBuilder.underline();
//        }
//
//        // Font size
//        final Font stdFont = workbook.getFontAt(0);
//        final short fontSize = font.getFontHeightInPoints();
//        if (stdFont.getFontHeightInPoints() != fontSize) {
//            styleBuilder.fontSize(fontSize, SizeUnit.PT);
//        }
//
//        // Font color
//        final short colorIndex = font.getColor();
//        if (font instanceof HSSFFont) {
//            if (colorIndex != HSSFFont.COLOR_NORMAL) {
//                final HSSFWorkbook wb = (HSSFWorkbook) workbook;
//                HSSFColor color = wb.getCustomPalette().getColor(colorIndex);
//                if (color != null) {
//                    short[] triplet = color.getTriplet();
//                    styleBuilder.foreground(triplet);
//                }
//            }
//        } else if (font instanceof XSSFFont) {
//            XSSFFont xssfFont = (XSSFFont) font;
//
//            XSSFColor color = xssfFont.getXSSFColor();
//            if (color != null) {
//                String argbHex = color.getARGBHex();
//                if (argbHex != null) {
//                    styleBuilder.foreground(argbHex.substring(2));
//                }
//            }
//        } else {
//            throw new IllegalStateException("Unexpected font type: " + (font == null ? "null" : font.getClass()) + ")");
//        }
//
//        // Background color
//        if (cellStyle.getFillPattern() == FillPatternType.SOLID_FOREGROUND) {
//            Color color = cellStyle.getFillForegroundColorColor();
//            if (color instanceof HSSFColor) {
//                short[] triplet = ((HSSFColor) color).getTriplet();
//                if (triplet != null) {
//                    styleBuilder.background(triplet);
//                }
//            } else if (color instanceof XSSFColor) {
//                String argb = ((XSSFColor) color).getARGBHex();
//                if (argb != null) {
//                    styleBuilder.background(argb.substring(2));
//                }
//            } else {
//                throw new IllegalStateException(
//                        "Unexpected color type: " + (color == null ? "null" : color.getClass()) + ")");
//            }
//        }
//
//        // alignment
//        switch (cellStyle.getAlignment()) {
//            case LEFT:
//                styleBuilder.leftAligned();
//                break;
//            case RIGHT:
//                styleBuilder.rightAligned();
//                break;
//            case CENTER:
//                styleBuilder.centerAligned();
//                break;
//            case JUSTIFY:
//                styleBuilder.justifyAligned();
//                break;
//            default:
//                // we currently don't support other alignment styles
//                break;
//        }
//
//        return styleBuilder.create();
//    }


}
