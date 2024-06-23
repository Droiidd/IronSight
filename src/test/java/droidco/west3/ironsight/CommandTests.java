package droidco.west3.ironsight;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandTests {
  private ServerMock server;
  private IronSight plugin;
  private PlayerMock player;

  @BeforeEach
  public void setUp() {
    server = MockBukkit.mock();
    plugin = MockBukkit.load(IronSight.class);
    player = server.addPlayer();
  }

  @Test
  public void suicideTest() {
    player.performCommand("suicide test");
    assertFalse(player.isDead());
    player.performCommand("suicide");
    assertTrue(player.isDead());
  }

  @AfterEach
  public void tearDown() {
    MockBukkit.unmock();
  }
}
