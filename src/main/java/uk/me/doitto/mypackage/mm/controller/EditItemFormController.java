package uk.me.doitto.mypackage.mm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import uk.me.doitto.mypackage.mm.object.Album;
import uk.me.doitto.mypackage.mm.object.Artist;
import uk.me.doitto.mypackage.mm.object.Track;

public class EditItemFormController extends AddItemFormController {

	protected void persistAlbum (Album album) {
		musicManagerService.saveAlbum(album);
	}
	
	protected void persistTrack (Track track) {
		musicManagerService.saveTrack(track);
	}
	
	protected void persistArtist (Artist artist) {
		musicManagerService.saveArtist(artist);
	}
	
	protected Map<String, Object> referenceData (HttpServletRequest request) throws ServletRequestBindingException {
		Map<String, Object> refData = new HashMap<String, Object>();
		if ((this.getCommandClass() == Album.class) || (itemClass == Album.class)) {
			refData.put("ArtistMap", musicManagerService.getArtistNameIdMap());			
			refData.put("TrackMap", musicManagerService.getTrackTitleIdMap());
		} else if ((this.getCommandClass() == Track.class) || (itemClass == Track.class)) {
			Map<String, String> artistMap = musicManagerService.getArtistNameIdMap();
			refData.put("AlbumMap", musicManagerService.getAlbumTitleIdMap());		
			refData.put("PerformerMap", artistMap);		
		} else if ((this.getCommandClass() == Artist.class) || (itemClass == Artist.class)) {
			Map<String, String> trackMap = musicManagerService.getTrackTitleIdMap();
			Map<String, String> artistMap = musicManagerService.getArtistNameIdMap();
			refData.put("MemberMap", artistMap);		
			refData.put("AlbumMap", musicManagerService.getAlbumTitleIdMap());		
			refData.put("BandMap", artistMap);			
			refData.put("PerformanceMap", trackMap);		
		}
		refData.put("EditMode", true);
		return refData;
	}

	protected Object formBackingObject (HttpServletRequest request) throws ServletRequestBindingException {
		if (itemClass == Album.class) {
			return musicManagerService.getAlbum(ServletRequestUtils.getRequiredStringParameter(request, "itemId"));
		} else if (itemClass == Track.class) {
			return musicManagerService.getTrack(ServletRequestUtils.getStringParameter(request, "itemId"));
		} else if (itemClass == Artist.class) {
			return musicManagerService.getArtist(ServletRequestUtils.getStringParameter(request, "itemId"));
		} else {
			return null;
		}
	}
}
