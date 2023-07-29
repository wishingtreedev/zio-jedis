package app.wishingtree.lib.zjedis

import redis.clients.jedis.Jedis
import zio.*
import opinions.*
import redis.clients.jedis.params.SetParams
import scala.jdk.CollectionConverters.*

extension (j: Jedis)
  def getZIO(key: String): Task[Option[String]] =
    ZIO.attemptBlocking {
      j.get(key).opt
    }

  def getZIO(key: Array[Byte]): Task[Option[Array[Byte]]] =
    ZIO.attemptBlocking {
      j.get(key).opt
    }

  def hgetZIO(key: String, field: String): Task[Option[String]] =
    ZIO.attemptBlocking {
      j.hget(key, field).opt
    }

  def hgetZIO(
      key: Array[Byte],
      field: Array[Byte],
  ): Task[Option[Array[Byte]]] =
    ZIO.attemptBlocking {
      j.hget(key, field).opt
    }

  def setZIO(key: String, value: String): Task[String] =
    ZIO.attemptBlocking {
      j.set(key, value)
    }

  def setZIO(
      key: String,
      value: String,
      params: SetParams,
  ): Task[Option[String]] =
    ZIO.attemptBlocking {
      j.set(key, value, params).opt
    }

  def setZIO(key: Array[Byte], value: Array[Byte]): Task[String] =
    ZIO.attemptBlocking {
      j.set(key, value)
    }

  def setZIO(
      key: Array[Byte],
      value: Array[Byte],
      params: SetParams,
  ): Task[Option[String]] =
    ZIO.attemptBlocking {
      j.set(key, value, params).opt
    }

  def hsetZIO(key: String, field: String, value: String): Task[Long] =
    ZIO.attemptBlocking {
      j.hset(key, field, value)
    }

  def hsetZIO(
      key: Array[Byte],
      field: Array[Byte],
      value: Array[Byte],
  ): Task[Long] =
    ZIO.attemptBlocking {
      j.hset(key, field, value)
    }

  def hsetZIO(key: String, hash: Map[String, String]): Task[Long] =
    ZIO.attemptBlocking {
      j.hset(key, hash.asJava)
    }

  def hsetZIO(
      key: Array[Byte],
      hash: Map[Array[Byte], Array[Byte]],
  ): Task[Long] =
    ZIO.attemptBlocking {
      j.hset(key, hash.asJava)
    }
