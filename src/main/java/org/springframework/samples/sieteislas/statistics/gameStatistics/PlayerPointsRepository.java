package org.springframework.samples.sieteislas.statistics.gameStatistics;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.sieteislas.player.Player;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Repository
public interface PlayerPointsRepository extends CrudRepository<PlayerPointsMap, Integer> {

    @Query("SELECT round(sum(points),2) FROM PlayerPointsMap")
    public Integer findTotalPoints();

    @Query("SELECT round(min(points),2) FROM PlayerPointsMap")
    public Integer findMinPoints();

    @Query("SELECT round(max(points),2) FROM PlayerPointsMap")
    public Integer findMaxPoints();

    @Query("SELECT round(avg(points),2) FROM PlayerPointsMap")
    public Integer findAvgPoints();

    @Query("SELECT round(sum(p.points),2) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    public Integer findTotalPointsUser(@Param("currentUser") String currentUser);

    @Query("SELECT round(min(p.points),2) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    public Integer findMinPointsUser(@Param("currentUser") String currentUser);

    @Query("SELECT round(max(p.points),2) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    public Integer findMaxPointsUser(@Param("currentUser") String currentUser);

    @Query("SELECT round(avg(p.points),2) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    public Integer findAvgPointsUser(@Param("currentUser") String currentUser);

    @Query("SELECT round(sum(p.gameStatistics.duration),2) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    public Double findTotalTimePlayedByUser(@Param("currentUser") String currentUser);

    @Query("SELECT round(min(p.gameStatistics.duration),2) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    public Double findMinTimePlayedByUser(@Param("currentUser") String currentUser);

    @Query("SELECT round(max(p.gameStatistics.duration),2) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    public Double findMaxTimePlayedByUser(@Param("currentUser") String currentUser);

    @Query("SELECT round(avg(p.gameStatistics.duration),2) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    public Double findAvgTimePlayedByUser(@Param("currentUser") String currentUser);

    @Query("SELECT count(p) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    public Integer getTotalNumberGamesByUser(@Param("currentUser") String currentUser);

    @Query("SELECT count(p) FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser GROUP BY p.gameStatistics.month, p.gameStatistics.year")
    public Collection<Integer> getGroupedNumberGamesByUser(@Param("currentUser") String currentUser);

    @Query("SELECT p.gameStatistics.id FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    List<Integer> findGameIdsUser(@Param("currentUser") String currentUser);

    @Query("SELECT p.gameStatistics.gameCreatorName FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    List<String> findGameCreatorsUser(@Param("currentUser") String currentUser);

    @Query("SELECT p.gameStatistics.duration FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    List<Double> findGameDurationsUser(@Param("currentUser") String currentUser);

    @Query("SELECT p.points FROM PlayerPointsMap p WHERE p.player.user.username LIKE :currentUser")
    List<Integer> findGamePointsUser(@Param("currentUser") String currentUser);

    @Query("SELECT sum(p.points) FROM PlayerPointsMap p GROUP BY p.player ORDER BY sum(p.points) DESC")
    List<Integer> findPointsRanked();

    @Query("SELECT p.player FROM PlayerPointsMap p GROUP BY p.player ORDER BY sum(p.points) DESC")
    List<Player> findUsersRankedByPoints();

    @Query("SELECT p FROM PlayerPointsMap p")
    List<PlayerPointsMap> findAll();

    @Query("SELECT p.points FROM PlayerPointsMap p WHERE p.gameStatistics.game.id = :gameId ORDER BY p.points DESC")
    List<Integer> findPointsEndGameRanked(@Param("gameId") Integer gameId);

    @Query("SELECT p FROM PlayerPointsMap p WHERE p.gameStatistics.game.id = :gameId ORDER BY p.points DESC")
    List<PlayerPointsMap> findMappingsEndGameRanked(@Param("gameId") Integer gameId);

    @Query("SELECT p.player.user.username FROM PlayerPointsMap p WHERE p.gameStatistics.game.id = :gameId ORDER BY p.points DESC")
    List<String> findUsernameEndGameRankedByPoints(@Param("gameId") Integer gameId);

}
