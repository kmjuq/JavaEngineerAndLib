package jep;

/**
 * {@link java.lang.constant.ConstantDesc}
 */
public class SealedJep {


    sealed interface IService
            permits Service {

    }

    sealed static class AbstractService
            permits Service {

    }

    final class Service extends AbstractService implements IService {

    }

}
