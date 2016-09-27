package com.tictac;

import com.tictac.domain.Game;
import com.tictac.domain.Position;
import com.tictac.service.GameLogic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan(value = "com.tictac.repository")
public class TictacApplication {

	public static void main(String[] args) {
		SpringApplication.run(TictacApplication.class, args);
		List<Position> pos = new ArrayList<>();
		pos.add(new Position(3,1,1));
		pos.add(new Position(2,2,2));
		pos.add(new Position(1,3,3));
		boolean test = GameLogic.checkDiagonalsOneLayerWin(pos);
		System.out.println(test);
	}

}
