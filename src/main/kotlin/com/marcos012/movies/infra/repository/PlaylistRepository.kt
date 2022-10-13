package com.marcos012.movies.infra.repository

import com.marcos012.movies.model.Playlist
import com.marcos012.movies.model.PlaylistId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlaylistRepository : JpaRepository<Playlist, PlaylistId> {
}