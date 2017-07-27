package com.cassandra.phantom.modeling.database

import com.cassandra.phantom.modeling.connector.Connector._
import com.cassandra.phantom.modeling.model.{ConcreteSongsByArtistModel, ConcreteSongsModel}
import com.outworkers.phantom.dsl._

/**
  * Created by thiago on 4/13/16.
  *
  * This is our Database object that wraps our two existing tables,
  * giving the ability to receive different connectors
  * for example: One for production and other for testing
  *
  */
class SongsDatabase(override val connector: KeySpaceDef) extends Database[SongsDatabase](connector) {

  object songsModel extends ConcreteSongsModel with connector.Connector

  object songsByArtistsModel extends ConcreteSongsByArtistModel with connector.Connector

}

/**
  * This is the production database, it connects to a secured cluster with multiple contact points
  */
object ProductionDb extends SongsDatabase(connector)

trait ProductionDatabaseProvider {
  def database: SongsDatabase
}

trait ProductionDatabase extends ProductionDatabaseProvider {
  override val database = ProductionDb
}