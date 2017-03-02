package config
import com.typesafe.config.ConfigFactory

/**
  * Created by Satya on 01/03/2017.
  */
object FileConfig {

  private lazy val config = ConfigFactory.load();
  lazy val allocationFolderLocation = {
    config.getString("folderLocations.allocation.value")
  }

}