package Jobs

import config.FileConfig
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Satya on 01/03/2017.
  */
object MainProcessorJob extends App {

  private val applicationName = "FileProcessor"
  private val cores = "local[2]"
  private val intervalSecs = 5
  start()

  def start(): Unit = {
    val sparkConf = new SparkConf(true)
    val sparkContext = new SparkContext(cores, applicationName, sparkConf)
    val sparkStreamingContext = new StreamingContext(sparkContext, Seconds(intervalSecs))
    val allocationStream = sparkStreamingContext.textFileStream(FileConfig.allocationFolderLocation)
    allocationStream.foreachRDD(x=> {

       x.collect().foreach(println)
    })
    sparkStreamingContext.start()

    sparkStreamingContext.awaitTermination()

  }
}
