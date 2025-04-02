package fr.mosef.scala.template.processor.impl


import fr.mosef.scala.template.processor.Processor
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

class ProcessorImpl() extends Processor {

  def process(inputDF: DataFrame, groupByColumn: String): DataFrame = {

    // Statistiques sur la dépression en fonction de la ville
    val depressionByCity = inputDF
      .groupBy("City")
      .agg(
        count(when(col("Depression") === 1, 1)).alias("Depressed Count"),
        count(when(col("Depression") === 0, 1)).alias("Not Depressed Count"),
        avg("Depression").alias("Average Depression")
      )

    // Statistiques sur la dépression en fonction de la profession
    val depressionByProfession = inputDF
      .groupBy("Profession")
      .agg(
        count(when(col("Depression") === 1, 1)).alias("Depressed Count"),
        count(when(col("Depression") === 0, 1)).alias("Not Depressed Count"),
        avg("Depression").alias("Average Depression")
      )

    // Statistiques sur la dépression en fonction de l'âge
    val depressionByAge = inputDF
      .groupBy("Age")
      .agg(
        count(when(col("Depression") === 1, 1)).alias("Depressed Count"),
        count(when(col("Depression") === 0, 1)).alias("Not Depressed Count"),
        avg("Depression").alias("Average Depression")
      )

    // Sélectionner le DataFrame en fonction du paramètre groupByColumn
    groupByColumn.toLowerCase match {
      case "city" => depressionByCity
      case "profession" => depressionByProfession
      case "age" => depressionByAge
      case _ =>
        println(s"Groupement non supporté: $groupByColumn, retour sur 'City' par défaut.")
        depressionByCity
    }

  }
}
