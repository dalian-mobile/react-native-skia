import { NavigationContainer } from "@react-navigation/native";
import React from "react";
import { createNativeStackNavigator } from "@react-navigation/native-stack";

import { HomeScreen, DrawingExample, AnimationExample } from "./Examples";
import { API } from "./API";

const App = () => {
  const Stack = createNativeStackNavigator();
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen
          name="Home"
          component={HomeScreen}
          options={{
            title: "🎨 Skia",
          }}
        />

        <Stack.Screen name="API" component={API} />
        <Stack.Screen name="Drawing" component={DrawingExample} />
        <Stack.Screen name="Animation" component={AnimationExample} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

// eslint-disable-next-line import/no-default-export
export default App;