package util;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public final class UiLog {
    private static final Logger ROOT = Logger.getLogger("stellarburgers.ui");

    static {
        ROOT.setUseParentHandlers(false);
        ROOT.addHandler(new ConsoleHandler() {
            @Override
            public void publish(LogRecord record) {
                if (!isLoggable(record)) {
                    return;
                }
                if (record.getLevel().intValue() >= Level.WARNING.intValue()) {
                    super.publish(record);
                } else {
                    System.out.print(getFormatter().format(record));
                }
            }
        });
    }

    private UiLog() { }

    public static Logger forClass(Class<?> type) {
        return Logger.getLogger(ROOT.getName() + "." + type.getName());
    }
}
