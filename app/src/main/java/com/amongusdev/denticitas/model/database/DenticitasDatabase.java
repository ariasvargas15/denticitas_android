package com.amongusdev.denticitas.model.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.amongusdev.denticitas.model.database.dao.ClienteDAO;
import com.amongusdev.denticitas.model.database.room.ClienteRoom;

//@Database(entities = {ClienteRoom.class}, version = 1)
public abstract class DenticitasDatabase extends RoomDatabase {
    //public abstract ClienteDAO getClienteDAO();
}
