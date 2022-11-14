package org.springframework.samples.sieteislas.player;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Players {
    
    private List<Player> players;

    @XmlElement
    public List<Player> getPlayerList() {
        if (players == null) {
            players = new ArrayList<>();
        }
        return players;
    }


}
