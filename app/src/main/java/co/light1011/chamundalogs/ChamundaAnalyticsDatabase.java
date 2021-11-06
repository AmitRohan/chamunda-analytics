package co.light1011.chamundalogs;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import co.light1011.chamundalogs.dao.ProductCDAO;
import co.light1011.chamundalogs.dao.TableCDAO;
import co.light1011.chamundalogs.dao.UserCDAO;
import co.light1011.chamundalogs.model.ProductC;
import co.light1011.chamundalogs.model.TableC;
import co.light1011.chamundalogs.model.UserC;

// REFER FOR MIGRATION
// https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929

// Update version when Schema changes

@Database(entities = {ProductC.class, UserC.class ,TableC.class}, version = 1, exportSchema = false)
public abstract class ChamundaAnalyticsDatabase extends RoomDatabase {

    public abstract ProductCDAO productCDAO();
    public abstract UserCDAO userCDAO();
    public abstract TableCDAO tableCDAO();

    private static volatile ChamundaAnalyticsDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ChamundaAnalyticsDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ChamundaAnalyticsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ChamundaAnalyticsDatabase.class, "chamunda_analytics_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
