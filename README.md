# Configuración de Primefaces
Proyecto para tutoriales de devpredator

NOTA: Esta documentación da por hecho que el desarrollador tiene conocimiento en Maven, por lo cual se omite la creación del arquetipo webapp del proyecto.

# Paso 1:
Agregar las dependencias de jsf y primefaces correspondientes en el archivo pom.xml

```
		<!-- https://mvnrepository.com/artifact/com.sun.faces/jsf-api -->
		<dependency>
			<groupId>com.sun.faces</groupId>
			<artifactId>jsf-api</artifactId>
			<version>2.2.20</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/com.sun.faces/jsf-api -->
		<dependency>
			<groupId>com.sun.faces</groupId>
			<artifactId>jsf-impl</artifactId>
			<version>2.2.20</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.primefaces/primefaces -->
		<dependency>
			<groupId>org.primefaces</groupId>
			<artifactId>primefaces</artifactId>
			<version>10.0.0</version>
		</dependency>
```

# Paso 2:
Agrega la siguiente configuración en el archivo web.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://java.sun.com/xml/ns/javaee"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
	id="WebApp_ID" version="3.0">
	<display-name>jsf</display-name>
	
	<context-param>
		<param-name>javax.faces.PROJECT_STAGE</param-name>
		<param-value>Development</param-value>
	</context-param>
	
	<context-param>
		<param-name>primefaces.THEME</param-name>
		<param-value>arya</param-value>
	</context-param>
	
	<servlet>
		<servlet-name>Faces Servlet</servlet-name>
		<servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
		<load-on-startup>1</load-on-startup>
	</servlet>
	
	<servlet-mapping>
		<servlet-name>Faces Servlet</servlet-name>
		<url-pattern>*.xhtml</url-pattern>
	</servlet-mapping>
	
	<welcome-file-list>
		<welcome-file>login.xhtml</welcome-file>
	</welcome-file-list>

</web-app>
```

# Paso 3

En caso de ocupar ECLIPSE IDE para la configuración del proyecto, ingresa a la siguiente ubicación de tu proyecto:

tutorial-primefaces -> .settings -> org.eclipse.wst.common.project.facet.core.xml

Al abrirlo cambia la siguiente línea

```
<installed facet="jst.web" version="2.3"/>
```

Por:

```
<installed facet="jst.web" version="3.0"/>
```

# Paso 4

En caso de ocupar ECLIPSE IDE, haz clic derecho en el proyecto y selecciona "Properties" -> "Deployment Assembly" -> "Add" -> "Java Build Path Entries" -> "Maven Dependencies" -> "Apply and Close"  

¡Y listo! Ejecuta el proyecto en la ubicación http://localhost:8080/tutorial-primefaces.
