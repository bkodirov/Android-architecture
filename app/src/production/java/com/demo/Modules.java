package com.demo;

final class Modules {
  static Object[] list(DemoApp app) {
    return new Object[] {
        new DemoappModule(app)
    };
  }

  private Modules() {
    // No instances.
  }
}
