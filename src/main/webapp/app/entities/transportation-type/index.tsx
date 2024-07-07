import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import TransportationType from './transportation-type';
import TransportationTypeDetail from './transportation-type-detail';
import TransportationTypeUpdate from './transportation-type-update';
import TransportationTypeDeleteDialog from './transportation-type-delete-dialog';

const TransportationTypeRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<TransportationType />} />
    <Route path="new" element={<TransportationTypeUpdate />} />
    <Route path=":id">
      <Route index element={<TransportationTypeDetail />} />
      <Route path="edit" element={<TransportationTypeUpdate />} />
      <Route path="delete" element={<TransportationTypeDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default TransportationTypeRoutes;
