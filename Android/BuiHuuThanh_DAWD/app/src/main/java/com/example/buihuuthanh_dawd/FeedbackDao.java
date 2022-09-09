package com.example.buihuuthanh_dawd;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface FeedbackDao {
    @Insert(onConflict = REPLACE)
    long insertFeedback(Feedback feedback);

    @Update
    int updateFeedback(Feedback feedback);

    @Delete
    int deleteFeedback(Feedback feedback);

    @Query("SELECT * FROM feedback")
    List<Feedback> getAllFeedback();

    @Query("SELECT * FROM feedback WHERE id = :id")
    Feedback getFeedback(int id);

    @Query("DELETE FROM feedback")
    void deleteAllFeedback();
}
