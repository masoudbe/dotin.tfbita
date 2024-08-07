import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './suggested-sanction-info.reducer';

export const SuggestedSanctionInfo = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const suggestedSanctionInfoList = useAppSelector(state => state.suggestedSanctionInfo.entities);
  const loading = useAppSelector(state => state.suggestedSanctionInfo.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="suggested-sanction-info-heading" data-cy="SuggestedSanctionInfoHeading">
        <Translate contentKey="tfbitaApp.suggestedSanctionInfo.home.title">Suggested Sanction Infos</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.suggestedSanctionInfo.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/suggested-sanction-info/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.suggestedSanctionInfo.home.createLabel">Create new Suggested Sanction Info</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {suggestedSanctionInfoList && suggestedSanctionInfoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.suggestedSanctionInfo.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('sanctionSerial')}>
                  <Translate contentKey="tfbitaApp.suggestedSanctionInfo.sanctionSerial">Sanction Serial</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sanctionSerial')} />
                </th>
                <th className="hand" onClick={sort('personnelCode')}>
                  <Translate contentKey="tfbitaApp.suggestedSanctionInfo.personnelCode">Personnel Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('personnelCode')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.suggestedSanctionInfo.draft">Draft</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {suggestedSanctionInfoList.map((suggestedSanctionInfo, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/suggested-sanction-info/${suggestedSanctionInfo.id}`} color="link" size="sm">
                      {suggestedSanctionInfo.id}
                    </Button>
                  </td>
                  <td>{suggestedSanctionInfo.sanctionSerial}</td>
                  <td>{suggestedSanctionInfo.personnelCode}</td>
                  <td>
                    {suggestedSanctionInfo.drafts
                      ? suggestedSanctionInfo.drafts.map((val, j) => (
                          <span key={j}>
                            <Link to={`/draft/${val.id}`}>{val.id}</Link>
                            {j === suggestedSanctionInfo.drafts.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/suggested-sanction-info/${suggestedSanctionInfo.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/suggested-sanction-info/${suggestedSanctionInfo.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/suggested-sanction-info/${suggestedSanctionInfo.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="tfbitaApp.suggestedSanctionInfo.home.notFound">No Suggested Sanction Infos found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default SuggestedSanctionInfo;
