package uk.me.doitto.mypackage.mm.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.throwaway.ThrowawayController;

import uk.me.doitto.mypackage.mm.object.Album;
import uk.me.doitto.mypackage.mm.object.Artist;
import uk.me.doitto.mypackage.mm.object.Track;
import uk.me.doitto.mypackage.mm.service.MusicManagerServiceIf;
import uk.me.doitto.mypackage.object.PersistentClass;

public class ListItemsController implements ThrowawayController {
	
	private MusicManagerServiceIf musicManagerService;	
	public void setManager(MusicManagerServiceIf manager) {
		this.musicManagerService = manager;
	}

	private Class<? extends PersistentClass> itemClass;
	public void setItemClass(Class<? extends PersistentClass> itemClass) {
		this.itemClass = itemClass;
	}
	
	public ModelAndView execute () {			
		if (itemClass == Album.class) {
			List<Album> list = musicManagerService.getAllAlbums();
			Collections.sort(list);
			return new ModelAndView("listAlbumsView", "albumList", list);
		} else if (itemClass == Track.class) {
			List<Track> list = musicManagerService.getAllTracks();
			Collections.sort(list);
			return new ModelAndView("listTracksView", "trackList", list);
		} else if (itemClass == Artist.class) {
			List<Artist> list = musicManagerService.getAllArtists();
			Collections.sort(list);
			return new ModelAndView("listArtistsView", "artistList", list);
		} else {
			return null;
		}
	}
}
