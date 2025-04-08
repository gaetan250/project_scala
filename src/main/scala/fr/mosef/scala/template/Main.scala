package fr.mosef.scala.template

import fr.mosef.scala.template.job.Job
import fr.mosef.scala.template.processor.Processor
import fr.mosef.scala.template.processor.impl.ProcessorImpl
import fr.mosef.scala.template.reader.Reader
import fr.mosef.scala.template.reader.impl.ReaderImpl
import org.apache.spark.sql.{DataFrame, SparkSession}
import fr.mosef.scala.template.writer.Writer
import org.apache.spark.SparkConf
import com.globalmentor.apache.hadoop.fs.BareLocalFileSystem
import org.apache.hadoop.fs.FileSystem



object Main extends App with Job {

  val cliArgs = args
  val MASTER_URL: String = try {
    cliArgs(0)
  } catch {
    case e: java.lang.ArrayIndexOutOfBoundsException => "local[1]"
  }
  val SRC_PATH: String = try {
    cliArgs(1)
  } catch {
    case _: ArrayIndexOutOfBoundsException =>
      println("❌ Aucun chemin d'entrée spécifié.")
      sys.exit(1)
  }

  val DST_PATH: String = try {
    cliArgs(2)
  } catch {
    case _: ArrayIndexOutOfBoundsException =>
      println("⚠️ Aucun chemin de sortie spécifié, utilisation de la sortie par défaut.")
      "src/main/resources/default-output"
  }

  val GROUP_BY_COLUMN: String = try {
    cliArgs(3)
  } catch {
    case _: ArrayIndexOutOfBoundsException =>
      println("❌ Aucune colonne spécifiée pour le groupement (ex: 'city').")
      sys.exit(1)
  }


  val conf = new SparkConf()
  conf.set("spark.driver.memory", "64M")
  conf.set("spark.testing.memory", "471859200")

  val sparkSession = SparkSession
    .builder
    .master(MASTER_URL)
    .config(conf)
    .appName("Scala Template")
    .enableHiveSupport()
    .getOrCreate()

  sparkSession
    .sparkContext
    .hadoopConfiguration
    .setClass("fs.file.impl",  classOf[BareLocalFileSystem], classOf[FileSystem])


  val reader: Reader = new ReaderImpl(sparkSession)
  val processor: Processor = new ProcessorImpl()
  val writer: Writer = new Writer(sparkSession)
  val src_path = SRC_PATH
  val dst_path = DST_PATH
  val groupByColumn = GROUP_BY_COLUMN

  val inputDF: DataFrame = reader.read(src_path)
  val processedDF: DataFrame = processor.process(inputDF, groupByColumn)
  processedDF.show(5)
  writer.write(processedDF, dst_path)}
