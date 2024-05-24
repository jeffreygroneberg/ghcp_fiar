package com.microsoft.germany.csu.fourinarow.services.fiar.api;

import com.microsoft.germany.csu.fourinarow.api.GameStatus;
import com.microsoft.germany.csu.fourinarow.persistence.entities.FiarGame;

public interface FiarService {

    GameStatus getGameStatus(FiarGame game);

} 
