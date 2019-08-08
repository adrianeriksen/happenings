import React, { useEffect, useState } from "react";

function App() {
  const [events, setEvents] = useState([]);

  useEffect(() => {
    fetch("/api/events")
      .then(res => res.json())
      .then(res => setEvents(res));
  });

  return <div>{JSON.stringify(events)}</div>;
}

export default App;
