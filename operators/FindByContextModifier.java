package operators;

import java.lang.reflect.Field;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

public class FindByContextModifier implements ElementLocatorFactory {

    private final SearchContext context;

    public FindByContextModifier(final SearchContext context) {
        this.context = context;
    }

    public ElementLocator createLocator(final Field field) {
        return new DefaultElementLocator(context, field);
    }

	
}


