package fr.mosef.scala.template.writer

import org.apache.spark.sql.{DataFrame, SparkSession}
import java.util.Properties
import java.io.FileInputStream

class Writer(spark: SparkSession) {

  private val props = new Properties()
  props.load(new FileInputStream("src/main/resources/application.properties"))

  private val format: String = props.getProperty("writer.format", "csv")
  private val separator: String = props.getProperty("writer.separator", ",")
  private val header: String = props.getProperty("writer.header", "true")
  private val mode: String = props.getProperty("writer.mode", "overwrite")
  private val coalesce: Boolean = props.getProperty("writer.coalesce", "false").toBoolean

  def write(df: DataFrame, path: String): Unit = {
    val dfToWrite = if (coalesce) df.coalesce(1) else df

    dfToWrite
      .write
      .format(format)
      .option("header", header)
      .option("sep", separator)
      .mode(mode)
      .save(path)
  }
}