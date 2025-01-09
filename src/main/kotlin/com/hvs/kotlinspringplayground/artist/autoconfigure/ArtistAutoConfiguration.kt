package com.hvs.kotlinspringplayground.artist.autoconfigure

import com.hvs.kotlinspringplayground.artist.repository.ArtistRepository
import com.hvs.kotlinspringplayground.artist.service.ArtistService
import com.hvs.kotlinspringplayground.spotify.service.SpotifyService
import com.hvs.kotlinspringplayground.outbox.autoconfigure.OutboxAutoConfiguration
import com.hvs.kotlinspringplayground.outbox.service.OutboxService
import com.hvs.kotlinspringplayground.tidal.service.TidalService
import com.hvs.kotlinspringplayground.user.service.UserService
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackageClasses = [ArtistRepository::class])
@Import(OutboxAutoConfiguration::class)
@EntityScan("com.hvs.kotlinspringplayground.artist.domain.jpa")

class ArtistAutoConfiguration {

    @Bean
    fun artistService(
        tidalService: TidalService,
        userService: UserService,
        artistRepository: ArtistRepository,
        spotifyService: SpotifyService,
        outboxService: OutboxService,
    ) = ArtistService(
        tidalService = tidalService,
        userService = userService,
        artistRepository = artistRepository,
        spotifyService = spotifyService,
        outboxService = outboxService,
    )
}