package tech.tablesaw.columns.geom;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import tech.tablesaw.api.ColumnType;
import tech.tablesaw.columns.AbstractColumnParser;

public class GeometryParser extends AbstractColumnParser<Geometry> {

  public GeometryParser(ColumnType columnType) {
    super(columnType);
  }

  @Override
  public boolean canParse(String s) {
    return false;
  }

  @Override
  public Geometry parse(String s) {
    if (isMissing(s)) {
      return (Geometry) GeometryColumnType.missingValueIndicator();
    }
    WKTReader reader = new WKTReader();
    try {
      return reader.read(s);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }
}
