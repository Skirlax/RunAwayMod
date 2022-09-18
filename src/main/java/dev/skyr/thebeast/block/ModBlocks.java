package dev.skyr.thebeast.block;

import dev.skyr.thebeast.TheBeast;
import dev.skyr.thebeast.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TheBeast.MODID);
    public static RegistryObject<Block> VIOLET_PORTAL = register("portal", () -> new Block(BlockBehaviour.Properties.of(Material.PORTAL)
            .requiresCorrectToolForDrops()
            .noCollission()
            ),CreativeModeTab.TAB_BUILDING_BLOCKS);



    public static void register(IEventBus bus) {
        BLOCKS.register(bus);

    }
    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> ret = BLOCKS.register(name, block);
        registerBlockItem(name, ret, tab);
        return ret;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, Supplier<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
}
