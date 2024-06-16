import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DraftFactor from './draft-factor';
import DraftFactorDetail from './draft-factor-detail';
import DraftFactorUpdate from './draft-factor-update';
import DraftFactorDeleteDialog from './draft-factor-delete-dialog';

const DraftFactorRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DraftFactor />} />
    <Route path="new" element={<DraftFactorUpdate />} />
    <Route path=":id">
      <Route index element={<DraftFactorDetail />} />
      <Route path="edit" element={<DraftFactorUpdate />} />
      <Route path="delete" element={<DraftFactorDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DraftFactorRoutes;
