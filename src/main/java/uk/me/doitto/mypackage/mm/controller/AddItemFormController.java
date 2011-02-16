package uk.me.doitto.mypackage.mm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

import uk.me.doitto.mypackage.mm.object.Album;
import uk.me.doitto.mypackage.mm.object.Artist;
import uk.me.doitto.mypackage.mm.object.Track;
import uk.me.doitto.mypackage.mm.service.MusicManagerServiceIf;
import uk.me.doitto.mypackage.object.Ownable;

public class AddItemFormController extends SimpleFormController {

	protected MusicManagerServiceIf musicManagerService;
	public void setManager(MusicManagerServiceIf manager) {
		this.musicManagerService = manager;
	}

	protected Class<? extends Ownable> itemClass;
	public void setItemClass(Class<? extends Ownable> itemClass) {
		this.itemClass = itemClass;
	}
	
	protected void persistAlbum (Album album) {
		musicManagerService.createAlbum(album);
	}
	
	protected void persistTrack (Track track) {
		musicManagerService.createTrack(track);
	}
	
	protected void persistArtist (Artist artist) {
		musicManagerService.createArtist(artist);
	}
	
	protected void initBinder (HttpServletRequest request, ServletRequestDataBinder binder) {		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy"), true));
	}

	public void doSubmitAction (Object command) {
		String[] ids;
		if ((this.getCommandClass() == Album.class) || (itemClass == Album.class)) {
			Album album = (Album)command;			
			ids = album.getLinkArtistIds();
			if (ids != null) {
				for (String id : ids) {
					musicManagerService.linkArtistToAlbum(id, album.getId());
				}
			}		
			ids = album.getLinkTrackIds();
			if (ids != null) {
				for (String id : ids) {
					musicManagerService.linkTrackToAlbum(id, album.getId());
				}
			}			
			persistAlbum(album);
		} else if ((this.getCommandClass() == Track.class) || (itemClass == Track.class)) {
			Track track = (Track)command;			
			ids = track.getLinkAlbumIds();
			if (ids != null) {
				for (String id : ids) {
					musicManagerService.linkTrackToAlbum(track.getId(), id);
				}
			}		
			ids = track.getLinkPerformerIds();
			if (ids != null) {
				for (String id : ids) {
					musicManagerService.linkPerformerToTrack(id, track.getId());
				}
			}			
//			ids = track.getLinkWriterIds();
//			if (ids != null) {
//				for (String id : ids) {
//					musicManagerService.linkWriterToTrack(id, track.getId());
//				}
//			}			
			persistTrack(track);
		} else if ((this.getCommandClass() == Artist.class) || (itemClass == Artist.class)) {
			Artist artist = (Artist)command;			
			ids = artist.getLinkMemberIds();
			if (ids != null) {
				for (String id : ids) {
					musicManagerService.linkMemberToBand(id, artist.getId());
				}
			}			
			ids = artist.getLinkBandIds();
			if (ids != null) {
				for (String id : ids) {
					musicManagerService.linkBandToMember(id, artist.getId());
				}
			}			
			ids = artist.getLinkAlbumIds();
			if (ids != null) {
				for (String id : ids) {
					musicManagerService.linkArtistToAlbum(artist.getId(), id);
				}
			}			
			ids = artist.getLinkPerformanceIds();
			if (ids != null) {
				for (String id : ids) {
					musicManagerService.linkPerformerToTrack(artist.getId(), id);
				}
			}			
//			ids = artist.getLinkWritingCreditIds();
//			if (ids != null) {
//				for (String id : ids) {
//					musicManagerService.linkWriterToTrack(artist.getId(), id);
//				}
//			}			
			persistArtist(artist);
		}
	}
}
