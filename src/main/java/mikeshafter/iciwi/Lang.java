package mikeshafter.iciwi;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.logging.Level;


public class Lang {
  
  private final Plugin plugin = Iciwi.getPlugin(Iciwi.class);
  private File file;
  private FileConfiguration config;
  
  // Fare gate strings
  // // Signs
  public String ENTRY = config.getString("entry");
  public String EXIT = config.getString("exit");
  public String VALIDATOR = config.getString("validator");
  public String FAREGATE = config.getString("faregate");
  //--- Messages
  public String REMAINING = config.getString("remaining");
  
  
  // Ticket Machine Strings
  //--- Global variables
  public String TICKET_MACHINE = config.getString("ticketMachine");
  public String TICKETS = config.getString("tickets");
  public String PLUGIN_NAME = config.getString("pluginName");
  public String CURRENCY = config.getString("currency");
  public String CARD_LORE_0 = config.getString("cardLore0");
  public String SERIAL_PREFIX = config.getString("serialPrefix");
  
  //--- Messages
  public String NOT_ENOUGH_MONEY = config.getString("notEnoughMoney");
  
  public void save() {
    if (config == null || file == null) {
      return;
    }
    try {
      get().save(file);
    } catch (IOException ex) {
      plugin.getLogger().log(Level.SEVERE, "Could not save config to "+file, ex);
    }
  }
  
  public FileConfiguration get() {
    return config;
  }
  
  public void reload() {
    if (file == null) {
      file = new File(plugin.getDataFolder(), "lang.yml");
    }
    config = YamlConfiguration.loadConfiguration(file);
    
    // Look for defaults in the jar
    Reader defaultLang = new InputStreamReader(Objects.requireNonNull(plugin.getResource("lang.yml")), StandardCharsets.UTF_8);
    YamlConfiguration def = YamlConfiguration.loadConfiguration(defaultLang);
    config.setDefaults(def);
  }
}
