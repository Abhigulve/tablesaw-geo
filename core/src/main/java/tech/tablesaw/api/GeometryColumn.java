package tech.tablesaw.api;

import com.google.common.base.Preconditions;
import com.vividsolutions.jts.geom.Geometry;
import it.unimi.dsi.fastutil.ints.IntComparator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import tech.tablesaw.columns.AbstractColumn;
import tech.tablesaw.columns.AbstractColumnParser;
import tech.tablesaw.columns.Column;
import tech.tablesaw.columns.geom.GeometryColumnType;
import tech.tablesaw.selection.Selection;

public class GeometryColumn extends AbstractColumn<GeometryColumn, Geometry> {

  private List<Geometry> data;
  private final IntComparator rowComparator =
      (i, i1) -> {
        Geometry f1 = get(i);
        Geometry f2 = get(i1);
        return f1.compareTo(f2);
      };

  public GeometryColumn(String name, List<Geometry> geometries) {
    super(GeometryColumnType.instance(), name);
    for (Geometry geom : geometries) {
      append(geom);
    }
  }

  public GeometryColumn(ColumnType type, String name) {
    super(type, name);
  }

  private GeometryColumn(String name) {
    super(GeometryColumnType.instance(), name);
  }

  @Override
  public int size() {
    return data.size();
  }

  @Override
  public Table summary() {
    Table summary = Table.create(this.name());
    //
    return summary;
  }

  @Override
  public Geometry[] asObjectArray() {
    return (Geometry[]) data.toArray();
  }

  @Override
  public int countMissing() {
    return 0;
  }

  @Override
  public String getString(int row) {
    if (GeometryColumnType.missingValueIndicator() == (get(row))) {
      return null;
    }
    return get(row).toString();
  }

  @Override
  public Geometry get(int row) {
    return data.get(row);
  }

  @Override
  public void clear() {
    data.clear();
  }

  @Override
  public void sortAscending() {}

  @Override
  public void sortDescending() {}

  @Override
  public boolean isEmpty() {
    return data.size() == 0;
  }

  @Override
  public IntComparator rowComparator() {
    return rowComparator;
  }

  @Override
  public Selection isMissing() {
    return null;
  }

  @Override
  public Selection isNotMissing() {
    return null;
  }

  @Override
  public int byteSize() {
    return 0;
  }

  @Override
  public byte[] asBytes(int rowNumber) {
    return new byte[0];
  }

  @Override
  public String getUnformattedString(int r) {
    return null;
  }

  @Override
  public boolean isMissing(int rowNumber) {
    return false;
  }

  @Override
  public Column<Geometry> setMissing(int i) {
    return null;
  }

  @Override
  public Column<Geometry> emptyCopy() {
    return null;
  }

  public static GeometryColumn create(String name) {
    return new GeometryColumn(name);
  }

  public GeometryColumn(ColumnType type, String name, List<Geometry> data) {
    super(type, name);
    this.data = data;
  }

  public static GeometryColumn create(String name, int size) {
    GeometryColumn column = new GeometryColumn(name, new ArrayList<>(size));
    for (int i = 0; i < size; i++) {
      column.appendMissing();
    }
    return column;
  }

  @Override
  public Column<Geometry> copy() {
    GeometryColumn newCol = create(name(), size());
    int r = 0;
    for (Geometry geom : this) {
      newCol.set(r, geom);
      r++;
    }
    return newCol;
  }

  @Override
  public Column<Geometry> emptyCopy(int rowSize) {
    return create(name());
  }

  @Override
  public Column<Geometry> lag(int n) {
    return null;
  }

  @Override
  public Column<Geometry> appendCell(String stringValue) {
    return appendCell(stringValue, GeometryColumnType.geometryParser);
  }

  @Override
  public Column<Geometry> appendCell(String stringValue, AbstractColumnParser<?> parser) {
    return appendObj(parser.parse(stringValue));
  }

  @Override
  public Column<Geometry> set(int row, Geometry value) {
    return null;
  }

  @Override
  public Column<Geometry> set(int row, Column<Geometry> sourceColumn, int sourceRow) {
    return null;
  }

  @Override
  public Column<Geometry> append(Geometry geometry) {
    data.add(geometry);
    return this;
  }

  @Override
  public Column<Geometry> append(Column<Geometry> column) {
    Preconditions.checkArgument(column.type() == this.type());
    GeometryColumn source = (GeometryColumn) column;
    final int size = source.size();
    for (int i = 0; i < size; i++) {
      append(source.get(i));
    }
    return this;
  }

  @Override
  public Column<Geometry> append(Column<Geometry> column, int row) {
    return null;
  }

  @Override
  public Column<Geometry> appendObj(Object value) {
    return null;
  }

  @Override
  public Column<Geometry> appendMissing() {
    return null;
  }

  @Override
  public Column<Geometry> where(Selection selection) {
    return null;
  }

  @Override
  public Column<Geometry> removeMissing() {
    return null;
  }

  @Override
  public Column<Geometry> unique() {
    return null;
  }

  @Override
  public Iterator<Geometry> iterator() {
    return null;
  }

  @Override
  public int compare(Geometry geometry, Geometry t1) {
    return 0;
  }
}
