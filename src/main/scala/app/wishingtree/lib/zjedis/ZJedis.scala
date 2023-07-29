package app.wishingtree.lib.zjedis

import opinions.*
import redis.clients.jedis.{ Jedis, JedisPool, JedisPoolConfig }
import zio.*

object ZJedis:

  val jedisPool: ZIO[
    Scope with ZJedisConfig,
    Throwable,
    JedisPool,
  ] =
    summon[Conversion[ZJedisConfig, JedisPoolConfig]]
    for
      z <- ZIO.service[ZJedisConfig]
      j <- ZIO.fromAutoCloseable {
             ZIO.attemptBlocking {
               z.password.fold {
                 new JedisPool(z, z.host, z.port, z.timeOut)
               } { p =>
                 new JedisPool(z, z.host, z.port, z.timeOut, p)
               }
             }
           }
    yield j

  val jedisPoolLayer: ZLayer[Scope with ZJedisConfig, Throwable, JedisPool] =
    jedisPool.zlayer

  val jedis: ZIO[JedisPool with Scope, Throwable, Jedis] =
    ZIO.serviceWithZIO[JedisPool] { p =>
      ZIO.fromAutoCloseable {
        ZIO.attempt {
          p.getResource
        }
      }
    }
