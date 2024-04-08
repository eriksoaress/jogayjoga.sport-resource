package jogayjoga.sport;

public class SportParser {
    public static Sport to(SportIn in ){
        return Sport.builder()
            .name(in.name())
            .build();
    }

    public static SportOut to(Sport in){
        return SportOut.builder()
            .id(in.id())
            .name(in.name())
            .build();
    }
}
