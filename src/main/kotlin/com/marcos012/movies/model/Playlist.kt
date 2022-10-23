package com.marcos012.movies.model

import org.jetbrains.annotations.NotNull
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "USER_PLAYLIST")
class Playlist(
    @EmbeddedId @NotNull val id: PlaylistId
)