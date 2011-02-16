package uk.me.doitto.mypackage.admin.object;

import java.util.EnumSet;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;

import uk.me.doitto.mypackage.globals.BitFieldAccess;
import uk.me.doitto.mypackage.object.PersistentClass;

public class Owner extends PersistentClass implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private static final EnumSet<SecurityRole> roles = EnumSet.allOf(SecurityRole.class);
	
	private static final EnumSet<ModuleFlag> modules = EnumSet.allOf(ModuleFlag.class);
	
	private int accountFlags, moduleFlags;
	
	private String password, pass1, pass2, role, notes;
	
	private String[] selectedModules;
	
	// for Hibernate
	public Owner () {
		super();
	}	
	// for search template
	public Owner (String username) {
		this();
		this.name = username;
	}
	
	@Override
	public String getUsername () {
		return name;
	}
	public void setUsername (String username) {
		this.name = username;
	}

	@Override
	public String getPassword () {
		return password;
	}
	public void setPassword (String password) {
		this.password = password;
	}
	
	public String getRole () {
		return role;
	}
	public void setRole (String role) {
		this.role = role;
	}

	public int getAccountFlags () {
		return accountFlags;
	}
	public void setAccountFlags (int accountFlags) {
		this.accountFlags = accountFlags;
	}

	public int getModuleFlags () {
		return moduleFlags;
	}
	public void setModuleFlags (int moduleFlags) {
		this.moduleFlags = moduleFlags;
	}
	
	public String getPass1 () {
		return pass1;
	}
	public void setPass1 (String pass1) {
		this.pass1 = pass1;
	}

	public String getPass2 () {
		return pass2;
	}
	public void setPass2 (String pass2) {
		this.pass2 = pass2;
	}

	public String getNotes () {
		return notes;
	}
	public void setNotes (String notes) {
		this.notes = notes;
	}
	
	public EnumSet<SecurityRole> getRoles () {
		return roles;
	}
	
	public EnumSet<ModuleFlag> getModules () {
		return modules;
	}

	public EnumSet<ModuleFlag> getCurrentModules () {
		EnumSet<ModuleFlag> moduleList = EnumSet.noneOf(ModuleFlag.class);
		for (ModuleFlag moduleFlag : modules) {
			if (BitFieldAccess.isFlag(moduleFlag, getModuleFlags())) {
				moduleList.add(moduleFlag);
			}
		}
		return moduleList;
	}

	public String[] getSelectedModules() {
		return selectedModules;
	}
	public void setSelectedModules (String[] selectedModules) {
		for (ModuleFlag moduleFlag : modules) {
			setModuleFlags(BitFieldAccess.setFlag(false, moduleFlag, getModuleFlags()));
			for (String moduleString : selectedModules) {
				if (moduleString.equals(moduleFlag.getCode())) {
					setModuleFlags(BitFieldAccess.setFlag(true, moduleFlag, getModuleFlags()));
				}
			}
		}
	}
	
	@Override
	public GrantedAuthority[] getAuthorities() {
		return new GrantedAuthority[] { new GrantedAuthorityImpl(getRole()) };
	}
	
	@Override
	public boolean isEnabled () {
		return BitFieldAccess.isFlag(AccountFlag.ENABLED, getAccountFlags());
	}
	public void setEnabled (boolean state) {
		setAccountFlags(BitFieldAccess.setFlag(state, AccountFlag.ENABLED, getAccountFlags()));
	}
	public void toggleEnabledStatus () {
		if (isEnabled()) {
			setEnabled(false);
		} else {
			setEnabled(true);
		}
	}

	@Override
	public boolean isAccountNonExpired () {
		return BitFieldAccess.isFlag(AccountFlag.ACCOUNT_NON_EXPIRED, getAccountFlags());
	}
	public void setAccountNonExpired (boolean state) {
		setAccountFlags(BitFieldAccess.setFlag(state, AccountFlag.ACCOUNT_NON_EXPIRED, getAccountFlags()));
	}

	@Override
	public boolean isAccountNonLocked () {
		return BitFieldAccess.isFlag(AccountFlag.ACCOUNT_NON_LOCKED, getAccountFlags());
	}
	public void setAccountNonLocked (boolean state) {
		setAccountFlags(BitFieldAccess.setFlag(state, AccountFlag.ACCOUNT_NON_LOCKED, getAccountFlags()));
	}

	@Override
	public boolean isCredentialsNonExpired () {
		return BitFieldAccess.isFlag(AccountFlag.CREDENTIALS_NON_EXPIRED, getAccountFlags());
	}
	public void setCredentialsNonExpired (boolean state) {
		setAccountFlags(BitFieldAccess.setFlag(state, AccountFlag.CREDENTIALS_NON_EXPIRED, getAccountFlags()));
	}
}
