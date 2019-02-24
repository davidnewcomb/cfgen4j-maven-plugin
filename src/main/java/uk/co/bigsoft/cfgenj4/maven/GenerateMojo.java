package uk.co.bigsoft.cfgenj4.maven;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import uk.co.bigsoft.apps.cfgen4j.cfg.Cfg;
import uk.co.bigsoft.apps.cfgen4j.gen.CfGen4jGenerator;

/**
 * Generate source
 *
 */
@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES, requiresProject = true)
public class GenerateMojo extends AbstractMojo {

	@Parameter(name = "spaceId", required = true)
	private String spaceId;
	@Parameter(name = "packageName", required = true)
	private String packageName;
	@Parameter(name = "accessToken", required = true)
	private String accessToken;

	@Parameter(name = "folder", defaultValue = "${project.build.directory}/generated-sources/cfgen4j")
	private File folder;
	@Parameter(name = "cacheFile")
	private File cacheFile;
	@Parameter(name = "coreEndpoint")
	private String coreEndpoint;
	@Parameter(name = "uploadEndpoint")
	private String uploadEndpoint;
	@Parameter(name = "addSetters", defaultValue = "false")
	private Boolean addSetters;
	@Parameter(name = "useDateType", defaultValue = "false")
	private Boolean useDateType;

	@Parameter(defaultValue = "${project}", required = true, readonly = true)
	private MavenProject project;

	public void execute() throws MojoExecutionException {

		getLog().info("Creating directory: " + folder.getAbsolutePath() + " added.");
		folder.mkdirs();

		Cfg c = cfgBuilder();
		new CfGen4jGenerator(c).run();

		getLog().info("Source directory: " + folder.getAbsolutePath() + " added.");
		project.addCompileSourceRoot(folder.getAbsolutePath());

	}

	private Cfg cfgBuilder() {

		Cfg c = new Cfg();

		if (spaceId != null) {
			c.setSpaceId(spaceId);
		}
		if (packageName != null) {
			c.setPackageName(packageName);
		}
		if (accessToken != null) {
			c.setAppAccessToken(accessToken);
		}
		if (folder != null) {
			c.setFolder(folder.getAbsolutePath());
		}
		if (addSetters != null) {
			c.setAddSetters(addSetters);
		}
		if (useDateType != null) {
			c.setUseDateType(useDateType);
		}
		if (cacheFile != null) {
			c.setCacheFile(cacheFile.getAbsolutePath());
		}
		if (coreEndpoint != null) {
			c.setCoreEndpoint(coreEndpoint);
		}
		if (uploadEndpoint != null) {
			c.setUploadEndpoint(uploadEndpoint);
		}
		return c;

	}
}
