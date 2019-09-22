import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

import { makeStyles } from '@material-ui/styles';
import { Container } from '@material-ui/core';

import Events from './Events';
import Login from './Login';
import UserContext from '../contexts/UserContext';
import Header from './Header';

const useStyles = makeStyles({
  container: {
    marginTop: 64
  }
});

function App() {
  const classes = useStyles();

  const [currentUser, setCurrentUser] = useState(null);

  useEffect(() => {
    fetch('/api/auth/token').then(async res => {
      if (res.status === 200) {
        const data = await res.json();
        setCurrentUser(data);
      }
    });
  }, []);

  return (
    <UserContext.Provider value={{ currentUser, setCurrentUser }}>
      <Router>
        <>
          <Header />
          <Container maxWidth="sm" className={classes.container}>
            <Switch>
              <Route path="/" exact component={Events} />
              <Route path="/login" component={Login} />
            </Switch>
          </Container>
        </>
      </Router>
    </UserContext.Provider>
  );
}

export default App;
