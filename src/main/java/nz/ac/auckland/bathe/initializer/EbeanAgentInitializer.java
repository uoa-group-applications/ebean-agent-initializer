package nz.ac.auckland.bathe.initializer;

import bathe.BatheInitializer;
import org.avaje.agentloader.AgentLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kefeng Deng (k.deng@auckland.ac.nz)
 */
public class EbeanAgentInitializer implements BatheInitializer {

	private static final Logger log = LoggerFactory.getLogger(EbeanAgentInitializer.class);

	/**
	 * The key of system property for agents
	 */
	public static final String AGENT_LOADING = "webapp.agents";

	/**
	 * Current initializer name
	 */
	public static final String INITIALIZER_NAME = "ebean-agent-initializer";

	/**
	 * Default Ebean ORM Agent
	 */
	public static final String DEFAULT_AGENTS = "avaje-ebeanorm-agent";

	/**
	 * Default Ebean Agent Parameters
	 */
	public static final String DEFAULT_AGENT_PARAMS = "debug=1;packages=nz/ac/auckland";

	@Override
	public int getOrder() {
		return 3;
	}

	@Override
	public String getName() {
		return INITIALIZER_NAME;
	}

	@Override
	public String[] initialize(String[] args, String jumpClass) {
		checkAndLoadAgent(AGENT_LOADING);
		return args;
	}

	protected void checkAndLoadAgent(String sysProp) {
		String pAgent = System.getProperty(sysProp);

		if (pAgent != null) {
			String[] agents = pAgent.split(",");
			for (String agent : agents) {
				agent = agent.trim();
				String extraParams = System.getProperty(sysProp + "." + agent);
				log.info("Ebean agent: {}, {}", agent, extraParams);
				AgentLoader.loadAgentFromClasspath(agent, extraParams);
			}
		} else {
			log.info("Ebean agent: {}, {}", DEFAULT_AGENTS, DEFAULT_AGENT_PARAMS);
			AgentLoader.loadAgentFromClasspath(DEFAULT_AGENTS, DEFAULT_AGENT_PARAMS);
		}
	}

}

