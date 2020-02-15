package qu.com.time;

import javafx.scene.input.DataFormat;

public class DataFormatThredLocal {

    private static final ThreadLocal<DataFormat> df = new ThreadLocal<>();
}
