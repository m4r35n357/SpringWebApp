package uk.me.doitto.mypackage.mm.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.throwaway.ThrowawayController;

import uk.me.doitto.mypackage.mm.object.Album;
import uk.me.doitto.mypackage.mm.object.Artist;
import uk.me.doitto.mypackage.mm.object.Track;
import uk.me.doitto.mypackage.mm.service.MusicManagerServiceIf;
import uk.me.doitto.mypackage.object.PersistentClass;

public class DeleteItemController implements ThrowawayController {

	// from Spring config
	private MusicManagerServiceIf musicManagerService;
	public void setManager(MusicManagerServiceIf manager) {
		this.musicManagerService = manager;
	}
	
	// from Spring config
	private Class<? extends PersistentClass> itemClass;
	public void setItemClass(Class<? extends PersistentClass> itemClass) {
		this.itemClass = itemClass;
	}
	
	// from HTTP request
	private String itemId;
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public ModelAndView execute () throws ServletException, IOException {
		if (itemClass == Album.class) {
			musicManagerService.deleteAlbum(musicManagerService.getAlbum(itemId));
			return new ModelAndView("listAlbumsView", "albumList", musicManagerService.getAllAlbums());
		} else if (itemClass == Track.class) {
			musicManagerService.deleteTrack(musicManagerService.getTrack(itemId));
			return new ModelAndView("listTracksView", "trackList", musicManagerService.getAllTracks());
		} else if (itemClass == Artist.class) {
			musicManagerService.deleteArtist(musicManagerService.getArtist(itemId));
			return new ModelAndView("listArtistsView", "artistList", musicManagerService.getAllArtists());
		} else {
			return null;
		}
	}
}
