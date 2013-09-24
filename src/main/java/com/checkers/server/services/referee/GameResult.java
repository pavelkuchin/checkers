package com.checkers.server.services.referee;

/**
 *
 *
 * @author Pavel Kuchin
 */
public enum GameResult {
    CONTINUE,           /// Game continues
    LOSING_BLACK_SURRENDER,    /// black surrender (by RESTful API)
    LOSING_BLACK_DESTROYED,    /// All blacks are destroyed
    LOSING_BLACK_LOCKED,       /// All blacks are locked
    LOSING_WHITE_SURRENDER,    /// white surrender (by RESTful API)
    LOSING_WHITE_DESTROYED,    /// All white are destroyed
    LOSING_WHITE_LOCKED,        /// All white are locked

    DEADHEAT_PLAYERS,                        /// Dead Heat by special RESTful API
    DEADHEAT_WITHOUT_FIGHT_32,               /// 32 steps without fight
    DEADHEAT_KING_3_AND_KING_1_15,           /// if one player has three kings another one king and no fight for 15 steps
    DEADHEAT_KING_3_AND_KING_1_ON_BIG_WAY_5, /// if one player has three kings another one king on big way and no fight for 5 steps
    DEADHEAT_FIGURE_2_AND_KING_1_10,         /// One player has two king or one king and one check another player has one king and no fight for 10 steps
    DEADHEAT_THE_SAME_COMBINATION_3_TIMES    /// The same combination 3 times
}
