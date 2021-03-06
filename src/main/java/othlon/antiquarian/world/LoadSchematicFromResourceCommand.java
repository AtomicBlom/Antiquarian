package othlon.antiquarian.world;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

public class LoadSchematicFromResourceCommand extends CommandBase
{

    @Override
    public String getCommandName()
    {
        return "aqLoadSchematicFromResource";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_)
    {
        return "aqLoadSchematicFromResource [schematicName]";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args)
    {
        if (sender instanceof EntityPlayerMP) {

            if (args.length < 1) {
                throw new CommandException("Could not execute command");
            }
            String filename = args[0];

            final EntityPlayerMP player = (EntityPlayerMP) sender;
            SchematicLoader loader = new SchematicLoader();
            final ResourceLocation schematicLocation = new ResourceLocation(String.format("Antiquarian:schematics/%s.schematic", filename));

            loader.loadSchematic(schematicLocation);
            loader.renderSchematicInOneShot(schematicLocation, player.getEntityWorld(), (int) player.posX, (int) player.posY, (int) player.posZ, ForgeDirection.NORTH, false);
            sender.addChatMessage(new ChatComponentText("Carrot Spawned."));
        }
    }
}
