package tech.tablesaw.geopackage;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import mil.nga.geopackage.GeoPackage;
import mil.nga.geopackage.features.user.FeatureDao;
import mil.nga.geopackage.features.user.FeatureResultSet;
import mil.nga.geopackage.manager.GeoPackageManager;
import org.junit.jupiter.api.Test;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.geopackage.GeoPackageReader;

public class GeoPackageReaderTest {

  public static FeatureResultSet readGpkFile(String fileName, final String layerName) {
    File geoPackageInputFile = null;
    GeoPackage geoPackage = null;
    try {
      geoPackageInputFile = new File(fileName);
      geoPackage = GeoPackageManager.open(geoPackageInputFile);
      // Feature and tile tables
      final List<String> features = geoPackage.getFeatureTables();
      final int layerId = validateAndGetLayer(features, layerName);
      // Query Features
      final FeatureDao featureDao = geoPackage.getFeatureDao(features.get(layerId));
      return featureDao.queryForAll();
    } finally {
      if (geoPackage != null) {
        //                geoPackage.close();
      }
    }
  }

  private static int validateAndGetLayer(final List<String> layers, final String inputLayer) {
    for (int i = 0; i < layers.size(); i++) {
      if (layers.get(i).equalsIgnoreCase(inputLayer)) {
        return i;
      }
    }
    return 0;
  }

  @Test
  public void Test_read() throws SQLException {
    FeatureResultSet test1 =
        readGpkFile(
            "/home/gulve/algorithms/tablesaw/core/src/test/java/tech/tablesaw/geopackage/test1.gpkg",
            "test1");
    Table read = GeoPackageReader.read(test1);
  }
}
