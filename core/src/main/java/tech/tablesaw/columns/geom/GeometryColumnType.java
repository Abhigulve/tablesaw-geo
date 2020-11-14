package tech.tablesaw.columns.geom;

import com.vividsolutions.jts.geom.Geometry;
import tech.tablesaw.api.ColumnType;
import tech.tablesaw.api.GeometryColumn;
import tech.tablesaw.columns.AbstractColumnParser;
import tech.tablesaw.columns.AbstractColumnType;
import tech.tablesaw.columns.Column;
import tech.tablesaw.io.ReadOptions;

public class GeometryColumnType extends AbstractColumnType {

  private static final byte BYTE_SIZE = 32;
  private static GeometryColumnType INSTANCE;
  public static final GeometryParser geometryParser = new GeometryParser(ColumnType.GEOM);

  protected GeometryColumnType(int byteSize, String name, String printerFriendlyName) {
    super(byteSize, name, printerFriendlyName);
  }

  public static GeometryColumnType instance() {
    if (INSTANCE == null) {
      INSTANCE = new GeometryColumnType(BYTE_SIZE, "GEOMETRY", "geometry");
    }
    return INSTANCE;
  }

  public static Object missingValueIndicator() {
    return null;
  }

  @Override
  public Column<?> create(String name) {
    return GeometryColumn.create(name);
  }

  @Override
  public AbstractColumnParser<?> customParser(ReadOptions options) {
    return null;
  }

  public static boolean valueIsMissing(Geometry geometry) {
    return geometry == null;
  }
}
