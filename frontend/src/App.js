import React, { useEffect, useState } from "react";
import Event from "./Event";

function App() {
  const [events, setEvents] = useState([]);

  useEffect(() => {
    fetch("/api/events")
      .then(res => res.json())
      .then(res => setEvents(res));
  });

  return (
    <div>
      <h1>Happenings</h1>
      {events.map(event => (
        <Event event={event} />
      ))}
    </div>
  );
}

export default App;
