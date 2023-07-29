package app.wishingtree.lib.zjedis
import opinions.*
import redis.clients.jedis.JedisPoolConfig
import zio.Layer
import zio.config.ReadError

import java.time.Duration

case class ZJedisConfig(
    host: String,
    port: Int,
    timeOut: Int,
    password: Option[String],
    minIdleConnections: Int,
    maxIdleConnections: Int,
    maxConnections: Int,
    testConnectionOnReturn: Boolean,
    testConnectionWhileIdle: Boolean,
    softMinEvictableIdleTimeMillis: Long,
)

object ZJedisConfig:

  given Conversion[ZJedisConfig, JedisPoolConfig] =
    (zConfig: ZJedisConfig) =>
      val jp = new JedisPoolConfig()
      jp.setMinIdle(zConfig.minIdleConnections)
      jp.setMaxIdle(zConfig.maxIdleConnections)
      jp.setMaxTotal(zConfig.maxConnections)
      jp.setTestOnReturn(zConfig.testConnectionOnReturn)
      jp.setTestWhileIdle(zConfig.testConnectionWhileIdle)
      jp.setSoftMinEvictableIdleTime(
        Duration.ofMillis(zConfig.softMinEvictableIdleTimeMillis),
      )
      jp

  val layer: Layer[ReadError[String], ZJedisConfig] =
    ConfigLayer[ZJedisConfig]("app.wishingtree.lib.zjedis.config")
