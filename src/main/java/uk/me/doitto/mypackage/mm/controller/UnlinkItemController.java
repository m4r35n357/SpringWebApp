package uk.me.doitto.mypackage.mm.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.throwaway.ThrowawayController;

import uk.me.doitto.mypackage.mm.service.MusicManagerServiceIf;

public class UnlinkItemController implements ThrowawayController {

	private MusicManagerServiceIf musicManagerService;
	public void setManager (MusicManagerServiceIf manager) {
		this.musicManagerService = manager;
	}
	
	private String albumId;
	public void setAlbumId (String albumId) {
		this.albumId = albumId;
	}
	
	private String trackId;
	public void setTrackId (String trackId) {
		this.trackId = trackId;
	}

	private String performanceId;
	public void setPerformanceId (String performanceId) {
		this.performanceId = performanceId;
	}

	private String artistId;
	public void setArtistId (String artistId) {
		this.artistId = artistId;
	}
	
	private String bandId;
	public void setBandId (String bandId) {
		this.bandId = bandId;
	}

	private String memberId;
	public void setMemberId (String memberId) {
		this.memberId = memberId;
	}

	private String performerId;
	public void setPerformerId (String performerId) {
		this.performerId = performerId;
	}

	private String fromAlbumId;
	public void setFromAlbumId (String fromAlbumId) {
		this.fromAlbumId = fromAlbumId;
	}
	
	private String fromTrackId;
	public void setFromTrackId (String fromTrackId) {
		this.fromTrackId = fromTrackId;
	}
	
	private String fromArtistId;
	public void setFromArtistId (String fromArtistId) {
		this.fromArtistId = fromArtistId;
	}
	
	public ModelAndView execute () throws Exception {
		if (fromAlbumId != null) {
			if (trackId != null){
				musicManagerService.unlinkTrackFromAlbum(trackId, fromAlbumId);
			}
			else if (artistId != null) {
				musicManagerService.unlinkArtistFromAlbum(artistId, fromAlbumId);
			}
			return new ModelAndView("listAlbumsView", "albumList", musicManagerService.getAllAlbums());
		} else if (fromTrackId != null) {
			if (albumId != null) {
				musicManagerService.unlinkTrackFromAlbum(fromTrackId, albumId);
			}
			else if (performerId != null) {
				musicManagerService.unlinkPerformerFromTrack(performerId, fromTrackId);
			}
			return new ModelAndView("listTracksView", "trackList", musicManagerService.getAllTracks());
		} else if (fromArtistId != null) {
			if (memberId != null) {
				musicManagerService.unlinkMemberFromBand(memberId, fromArtistId);
			}
			else if (bandId != null) {
				musicManagerService.unlinkBandFromMember(bandId, fromArtistId);
			}
			else if (albumId != null) {
				musicManagerService.unlinkArtistFromAlbum(fromArtistId, albumId);
			}
			else if (performanceId != null) {
				musicManagerService.unlinkPerformerFromTrack(fromArtistId, performanceId);
			}
			return new ModelAndView("listArtistsView", "artistList", musicManagerService.getAllArtists());
		} else {
			return null;
		}
	}
}
