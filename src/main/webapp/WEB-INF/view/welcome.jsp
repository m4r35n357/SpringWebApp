<div id="content">

	<p>
		This Web Application is a testbed for various technologies, woven together using the Spring Framework, and protected using ACEGI security.
	</p>
	<p>
		Back-end: A choice of PostgreSQL/HSQLDB databases accessed via Hibernate ORM and Spring's Hibernate Template using a generic DAO.  Database inheritance follows the "table per class" model.
	</p>
	<p>
		Front-end: Spring MVC/Tiles with plain JSP/JSTL pages and tag files, alongside DWR-based AJAX (including Comet) view technologies.  A server-side clock is provided as a working example.
	</p>
	<p>
		Selected functionality is also exposed remotely via Spring Web Services (using AXIS), and Spring's HTTP-invoker, also Caucho's Hessian and Burlap protocols.
	</p>
	<p>
		Two "application" modules - Music Manager (including an applet for OGG audio/video) and RSS aggregator (multiple aggregated feeds per user, automatic background feed retrieval and caching).
	</p>
	<p>
		User management, can be used to control account status, authorization roles and access to available application modules.
	</p>
	<p>
		Exceptions are reported by email using a Spring AOP advisor and interceptor.
	</p>
	<p>
		There is built-in theming and Internationalization support, and basic themes and localization are provided.
	</p>
	
</div>
