package uz.softclub.telegram;


import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.util.AnnotationLiteral;

//import ch.zuehlke.bench.skiarea.TitlisService;
//import ch.zuehlke.bench.transport.DelayService;
//import ch.zuehlke.bench.weather.FoehnService;
//import ch.zuehlke.bench.weather.WeatherService;

@ApplicationScoped
public class CommandExecutor {

    private static final String PREFIX = "/";


    String execute(COMMAND currentCommand) {
        TelegramCommand cmd = (TelegramCommand) CDI.current()
                .select(/*currentCommand.clazz,*/ new AnnotationLiteral<Any>() {})
                .get();
        return cmd.execute(currentCommand.parameter);
    }

    static Optional<COMMAND> parseCommand(String command) {
        if (command == null || command.isEmpty() || !command.startsWith(PREFIX)) {
            return Optional.empty();
        }

        String plainCmd = command.substring(1);

        for (COMMAND cmd : COMMAND.values()) {
            if (plainCmd.equalsIgnoreCase(cmd.name())) {
                return Optional.of(cmd);
            }
        }
        return Optional.empty();
    }

    enum COMMAND {
        LUZ_BRN(false, "luzern", "bern", "interregio"),
        BRN_LUZ(false, "bern", "luzern", "interregio"),
        LUZ_HOM(false, "luzern-bus", "home"),
        HOM_LUZ(false, "home", "luzern-bus"),
        LUZ_BAS(false, "Luzern", "basel"),
        BAS_LUZ(false, "basel", "luzern"),
        LUZ_ZRH(false, "luzern", "zürich"),
        ZRH_LUZ(false, "zürich", "luzern"),
        BAS_BRN(false, "basel", "bern"),
        BRN_BAS(false, "bern", "basel"),
        LUZ( false, "LUZ"),
        TIT( false, "TIT"),
        BER( false, "BER"),
        BAS( false, "BAS"),
        ENG( false, "ENG"),
        ALT( false, "ALT"),
        LIFT(false, new String[]{}),
        FOEHN(true, new String[]{});
//        LUZ_BRN(DelayService.class, false, "luzern", "bern", "interregio"),
//        BRN_LUZ(DelayService.class, false, "bern", "luzern", "interregio"),
//        LUZ_HOM(DelayService.class, false, "luzern-bus", "home"),
//        HOM_LUZ(DelayService.class, false, "home", "luzern-bus"),
//        LUZ_BAS(DelayService.class, false, "Luzern", "basel"),
//        BAS_LUZ(DelayService.class, false, "basel", "luzern"),
//        LUZ_ZRH(DelayService.class, false, "luzern", "zürich"),
//        ZRH_LUZ(DelayService.class, false, "zürich", "luzern"),
//        BAS_BRN(DelayService.class, false, "basel", "bern"),
//        BRN_BAS(DelayService.class, false, "bern", "basel"),
//        LUZ(WeatherService.class, false, "LUZ"),
//        TIT(WeatherService.class, false, "TIT"),
//        BER(WeatherService.class, false, "BER"),
//        BAS(WeatherService.class, false, "BAS"),
//        ENG(WeatherService.class, false, "ENG"),
//        ALT(WeatherService.class, false, "ALT"),
//        LIFT(TitlisService.class, false, new String[]{}),
//        FOEHN(FoehnService.class, true, new String[]{});


//        private Class clazz;
        private boolean photo;
        private String[] parameter;

        COMMAND(/*Class clazz,*/ boolean photo, String... parameter) {
//            this.clazz = clazz;
            this.photo = photo;
            this.parameter = parameter;
        }

        public String[] getParameter() {
            return parameter;
        }

        public boolean isPhoto() {
            return photo;
        }
    }
}

