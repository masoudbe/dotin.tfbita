import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import SuggestedSanctionInfo from './suggested-sanction-info';
import SuggestedSanctionInfoDetail from './suggested-sanction-info-detail';
import SuggestedSanctionInfoUpdate from './suggested-sanction-info-update';
import SuggestedSanctionInfoDeleteDialog from './suggested-sanction-info-delete-dialog';

const SuggestedSanctionInfoRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<SuggestedSanctionInfo />} />
    <Route path="new" element={<SuggestedSanctionInfoUpdate />} />
    <Route path=":id">
      <Route index element={<SuggestedSanctionInfoDetail />} />
      <Route path="edit" element={<SuggestedSanctionInfoUpdate />} />
      <Route path="delete" element={<SuggestedSanctionInfoDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default SuggestedSanctionInfoRoutes;
