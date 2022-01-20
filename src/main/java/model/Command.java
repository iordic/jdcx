package model;


public class Command {
    public static final char CONNECT = 'Q';
    public static final char DISCONNECT = 'W';
    public static final char OUTPUT_VOLTAGE_SET = 'V';
    public static final char OUTPUT_CURRENT_SET = 'I';
    public static final char PROTECTION_TIME_ENABLE = 'X';
    public static final char PROTECTION_TIME_DISABLE = 'Y';
    public static final char PROTECTION_HOUR_SET = 'H';
    public static final char PROTECTION_MINUTE_SET = 'M';
    public static final char PROTECTION_SECOND_SET = 'S';
    public static final char PROTECTION_VOLTAGE_SET = 'B';
    public static final char PROTECTION_CURRENT_SET = 'D';
    public static final char PROTECTION_POWER_SET = 'E';
    public static final char LOAD_M1_PRESET = 'O';
    public static final char LOAD_M2_PRESET = 'P';
    public static final char RESET_STATUS = 'Z';
    public static final char TURN_DC_ON = 'N';
    public static final char TURN_DC_OFF = 'F';
    public static final char UPDATE = 'U';  // Not implemented
}
